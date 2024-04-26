package core;

import gameComponents.GameFrame;
import gameComponents.GameGraphicPanel;

import java.awt.*;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 *
 * @author Roze Behzad
 * @version 1.0
 */
public class Race
{
    int raceLength;
    core.Horse[] horses;
    public GameGraphicPanel gameGraphicPanel;
    private HorseManager horseManager = HorseManager.getInstance();

    private GameFrame parentFrame;

    core.Horse winner;

    /**
     * Constructor for objects of class Core.Race
     * Initially there are no horses in the lanes
     *
     * @param distance           the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, core.Horse[] horses, GameGraphicPanel gameGraphicPanel, GameFrame parentFrame) {
        raceLength = distance;
        this.horses = horses; // array to hold horses
        this.gameGraphicPanel = gameGraphicPanel;
        this.parentFrame = parentFrame;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(core.Horse theHorse, int laneNumber) {
        horses[laneNumber - 1] = theHorse; // improved adding of horses to their lanes
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {

        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        int timeStamp = 0;

        //reset all the lanes (all horses not fallen and back to 0).
        for (core.Horse horse : horses) {
            horse.goBackToStart();
        }

        boolean interrupted = false; // tracks when race is interrupted

        while (!finished) {

            if (Thread.interrupted()) { // handles thread interruption
                interrupted = true;
                break;
            }

            boolean allFallen = true;
            //if any of the three horses has won the race is finished
            for (core.Horse horse : horses) {
                if (raceWonBy(horse, timeStamp)){
                    finished = true;
                    this.winner = horse;
                }
                if (!horse.hasFallen()){
                    allFallen = false;
                }
            }

            if (allFallen){
                finished = true;
//                ui.updateWinner();
            }

            for (int i = 0; i < horses.length; i++) {
                //move each horse
                moveHorse(horses[i], timeStamp);
                //print the race positions
                printRace();
            }
            gameGraphicPanel.updateGraphicPanel();



            //wait for 100 milliseconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                interrupted = true;
                break;
            }
            timeStamp++;
        }
        if(this.winner == null){
            this.winner = new core.Horse('A', "No Winner", 0, null, null);
        }
        if(interrupted){
            this.winner = new core.Horse('A', "Terminated", 0, null, null);
        }
        RaceStat raceStat = new RaceStat(raceLength, this.winner.getName(), timeStamp, horses, horses.length);
        parentFrame.onRaceStop(raceStat);

    }

    public void stopRace(){
        //reset all the lanes (all horses not fallen and back to 0).
        for (core.Horse horse : horses) {
            horse.goBackToStart();
        }
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */

    private void moveHorse(core.Horse theHorse, int timeStamp)
    {
        //if the horse has fallen it cannot move,
        //so only run if it has not fallen

        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }

            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall(timeStamp);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(core.Horse theHorse, int timeStamp)
    {
        if (theHorse.getDistanceTravelled() == raceLength)
        {
            theHorse.win(timeStamp);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window

        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        for ( core.Horse horse : horses ){
            printLane(horse);
            System.out.println();
        }

        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    public void updateUI(Graphics2D g2){

        for (int x = 0; x < horses.length ; x++){
            g2.drawImage(
                    horseManager.getHorseImage(horses[x]),
                    horses[x].getDistanceTravelled() * (gameGraphicPanel.tileSize / 4) + gameGraphicPanel.tileSize,
                    (x+1) * gameGraphicPanel.tileSize * 2,
                    gameGraphicPanel.tileSize,
                    gameGraphicPanel.tileSize,
                    null);
        }

    }
}
