package com.travel.travtronics.dto;

import static java.util.Collections.emptySet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

	private static final String SPLIT_CHAR = ",";

	@Override
	public String convertToDatabaseColumn(Set<String> stringList) {
		// TODO Auto-generated method stub
		return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
	}

	@Override
	public Set<String> convertToEntityAttribute(String string) {
		// TODO Auto-generated method stub
		return string != null ? new HashSet<>(Arrays.asList(string.split(SPLIT_CHAR))) : emptySet();
	}

}
