package edu.exam.manager.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * Created by Davi on 7/4/2017.
 */
public class ExcelUtils {

    /**
     * check if cell is not null and cell type is String.
     * @param cell the cell to check.
     * @return TRUE if cell is not null and cell type is String.
     */
    protected static Boolean isString(Cell cell) {
        return cell != null && cell.getCellTypeEnum() == CellType.STRING;
    }

}
