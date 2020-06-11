package biblio.testSwing;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import biblio.domain.Exemplaire;

public class ModelTableau extends AbstractTableModel{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private ArrayList<Exemplaire> data = new ArrayList<Exemplaire>();

private String[] headers;;

public ModelTableau(ArrayList<Exemplaire> data, String[] headers){
    super();
    this.data=data;
    this.headers=headers;
}

public int getRowCount() {
    return data.size();
}

public int getColumnCount() {
    return headers.length;
}

public void removeRow(int row) {
    data.remove(row);
    fireTableRowsDeleted(row, row);
}

public void setColumnName(int i, String name) {
    headers[i] = name;
    fireTableStructureChanged();
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
	// TODO Auto-generated method stub
	return null;
}

}