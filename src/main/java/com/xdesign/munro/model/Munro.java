package com.xdesign.munro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Ricardo Vargas
 */
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Munro {

	@Id
	@GeneratedValue
	@CsvBindByPosition(position = 0)
	int runningNo;

	@CsvBindByPosition(position = 1)
	int doBIHNumber;

	@CsvBindByPosition(position = 2)
	String streetmap;

	@CsvBindByPosition(position = 3)
	String geograph;

	@CsvBindByPosition(position = 4)
	String hillBagging;

	@CsvBindByPosition(position = 5)
	String name;

	@CsvBindByPosition(position = 6)
	int smcSection;

	@CsvBindByPosition(position = 7)
	String rhbSection;

	@CsvBindByPosition(position = 8)
	double section;

	@CsvBindByPosition(position = 9)
	double heightMeters;

	@CsvBindByPosition(position = 10)
	double heightFt;

	@CsvBindByPosition(position = 11)
	String map150;

	@CsvBindByPosition(position = 12)
	String map125;

	@CsvBindByPosition(position = 13)
	String gridRef;

	@CsvBindByPosition(position = 14)
	String gridRefXY;

	@CsvBindByPosition(position = 15)
	String xcoord;

	@CsvBindByPosition(position = 16)
	String ycoord;

	@CsvBindByPosition(position = 17)
	String n1891;

	@CsvBindByPosition(position = 18)
	String n1921;

	@CsvBindByPosition(position = 19)
	String n1933;

	@CsvBindByPosition(position = 20)
	String n1953;

	@CsvBindByPosition(position = 21)
	String n1969;

	@CsvBindByPosition(position = 22)
	String n1974;

	@CsvBindByPosition(position = 23)
	String n1981;

	@CsvBindByPosition(position = 24)
	String n1984;

	@CsvBindByPosition(position = 25)
	String n1990;

	@CsvBindByPosition(position = 26)
	String n1997;

	@CsvBindByPosition(position = 27)
	String post1997;

	@CsvBindByPosition(position = 28)
	String comments;

}