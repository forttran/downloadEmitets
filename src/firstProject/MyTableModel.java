package firstProject;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModel implements TableModel {
	
	
	
	private ArrayList<emitets> emitets;
	
	public MyTableModel(ArrayList<emitets> emitets) {
		this.emitets = emitets;
	}


	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Эмитет";
		case 1:
			return "Загрузка";
		}
		return "";
	}

	public int getRowCount() {
		return emitets.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		emitets emitet = emitets.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return emitet.names;
		case 1:
			return  "Загружен";
		}
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

  

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
}