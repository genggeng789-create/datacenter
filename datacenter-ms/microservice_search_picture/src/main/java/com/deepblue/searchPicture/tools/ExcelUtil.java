package com.deepblue.searchPicture.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepblue.searchPicture.entity.PictureResource;
import com.deepblue.searchPicture.service.ExtractPictureResourceService;
import javafx.geometry.Pos;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.DigestUtils;
import com.deepblue.searchPicture.entity.Position;

public abstract class ExcelUtil {

    public static void main(String[] args) throws Exception {
//        String fileName = "C:\\Users\\tuomy\\Desktop\\2020-11-18-05-22.xlsx";
//        readExcel(fileName);
        System.out.println(DigestUtils.md5DigestAsHex("矿车道路.zip".getBytes()).toUpperCase());
    }

    protected Map<String, Position> readExcel1(String fileName, Map<String,String> map2,Map<Position, Object> map)
    {
        Map<String, Position> map1 = new HashMap<>();
        Workbook workbook = null;
        Row row = null;
        //获取Excel文档
        workbook = getWorkbook(fileName);
        //获取Excel文档的第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);
        //获取文档中已保存数据的行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        row = sheet.getRow(0);
        //获取当前行已保存数据的最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i);
            if (null != row)
            {
                for (int j = 0; j < colnum; j++) {
                    Cell cell = row.getCell(j);
                    if(map2.get((String)getValueFromCell(cell))!=null){
                        Position position = new Position(i,j+1);
                        map1.put(map2.get((String)getValueFromCell(cell)),position);
                        map.put(position,"");
                        //System.out.println(getValueFromCell(cell));
                    }
                    //System.out.println(getValueFromCell(cell));
                }
            }
        }
        return map1;
    }

    protected void readExcel(String fileName, Map<Position, Object> map) {
        Workbook workbook = null;
        Row row = null;
        //获取Excel文档
        workbook = getWorkbook(fileName);
        //获取Excel文档的第一个sheet页
        Sheet sheet = workbook.getSheetAt(0);
        //获取文档中已保存数据的行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        row = sheet.getRow(0);
        //获取当前行已保存数据的最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i);
            if (null != row)
            {
                for (int j = 0; j < colnum; j++) {
                    Cell cell = row.getCell(j);
                    Position position = new Position(i,j);
                    if(map.get(position)!=null){
                        map.put(position,getValueFromCell(cell));
                        //System.out.println(getValueFromCell(cell));
                    }
                    //System.out.println(getValueFromCell(cell));
                }
            }
        }
    }

    private static Workbook getWorkbook(String fileName) {//根据后缀获取Excel表格
        Workbook workbook = null;
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
            if ("xls".equals(suffix))
            {
                workbook = new HSSFWorkbook(in);
            }
            else if ("xlsx".equals(suffix))
            {
                workbook = new XSSFWorkbook(in);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    private static Object getValueFromCell(Cell cell) {//获取单元格的值
        Object value = null;
        if (null == cell)
        {
            return "";
        }
        //判断cell类型
        switch(cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:{
                value = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_FORMULA:{
                //判断cell是否为日期格式
                if(DateUtil.isCellDateFormatted(cell)){
                    //转换为日期格式YYYY-mm-dd
                    value = cell.getDateCellValue();
                }else{
                    //数字
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING:{
                value = cell.getRichStringCellValue().getString();
                break;
            }
            default:
                value = "";
        }
        return value;
    }
}
