package com.xdesign.munro.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MunroDTO {
	String name;
	double heightMeters;
	String category;
	String gridReference;
}
