package gameComponents;

import theme.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import java.awt.*;

public class GameTable extends JTable {
    public GameTable(int numRows, int numColumns) {
        super(numRows, numColumns);
        initializeTable();
    }

    public GameTable(TableModel tableModel) {
        super(tableModel);
        initializeTable();
    }

    public GameTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        initializeTable();
    }

    private void initializeTable() {
        setBackground(Color.TableBackgroundColor);
        setBorder(new LineBorder(Color.TableBorderColor));
        setGridColor(Color.TableBorderColor);
        setForeground(Color.PanelTextColor);
    }
}
