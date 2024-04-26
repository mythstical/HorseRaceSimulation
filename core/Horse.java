package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;


/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 *
 * @author Roze Behzad
 * @version 1.0
 */

public class Horse implements Serializable
{
    private boolean fallen;
    private double confidence;
    private char symbol;
    private String name;
    private int distanceTravelled;
    private Breed breed;
    private HorseColor coatColor;
    private int racesWon;
    private int racesLost;
    private int totalDistanceTravelled;
    private int totalRunningTime;
    private int imgFrameChanger = 0;


    //Constructor of class Core.Horse
    /**
     * Constructor for objects of class Core.Horse
     */
    public Horse(char horseSymbol, String horseName,  double horseConfidence, Breed breed, HorseColor color)
    {
        this.fallen = false;
        this.confidence = horseConfidence;
        this.symbol = horseSymbol;
        this.name = horseName;
        this.breed = breed;
        this.coatColor = color;
        this.distanceTravelled = 0;
    }




    //Other methods of class Horse
    public void fall(int timeStamp)
    {
        this.fallen = true;
        this.confidence = this.confidence - 0.1;
        this.totalDistanceTravelled += distanceTravelled;
        this.totalRunningTime += timeStamp;
        this.racesLost+=1;
    }

    public void win(int timeStamp)
    {
        this.confidence = this.confidence + 0.1;
        this.totalDistanceTravelled += distanceTravelled;
        this.totalRunningTime += timeStamp;
        this.racesWon+=1;
    }

    public double getConfidence()
    {
        return this.confidence;
    }

    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }


    public String getName()
    {
        return this.name;
    }

    public char getSymbol()
    {
        return this.symbol;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }

    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public HorseColor getCoatColor() {
        return coatColor;
    }

    public void setCoatColor(HorseColor color) {
        this.coatColor = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRacesWon() {
        return racesWon;
    }

    public int getRacesLost() {
        return racesLost;
    }

    public double getAvgSpeed() {
        return (double) this.totalDistanceTravelled / this.totalRunningTime;
    }

    public int getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }
