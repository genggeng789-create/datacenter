package com.deepblue.searchPicture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

 @Data
@AllArgsConstructor
public class Position {
    int row;
    int column;

    @Override
    public boolean equals(Object o){
        Position p = (Position) o;
        return (this.row == p.row && this.column == p.column);
    }

    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + column;
        return result;
    }
}
