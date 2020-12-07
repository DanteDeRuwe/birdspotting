package com.dantederuwe.birdspotting.domain;

import com.dantederuwe.birdspotting.validation.PastOrPresentYear;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

public class BirdSpecie {

	@NotBlank
	private String name;

	@PastOrPresentYear(message = "{validation.specie.yearOfDiscovery.PastOrPresentYear}")
	@Min(value = 1250, message = "{validation.specie.yearOfDiscovery.Min}")
	@NotNull
    private Integer yearOfDiscovery;

	@Pattern(regexp = "([A-Z]{2}\\d{3})?", message = "{validation.specie.code}")
	@NotBlank
    private String code;
		
	public BirdSpecie(String name, Integer yearOfDiscovery, String code) {
		super();
		this.name = name;
		this.yearOfDiscovery = yearOfDiscovery;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearOfDiscovery() {
		return yearOfDiscovery;
	}

	public void setYearOfDiscovery(Integer yearOfDiscovery) {
		this.yearOfDiscovery = yearOfDiscovery;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return String.format("%s - %d (%s)", getName(), getYearOfDiscovery(), getCode());
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		return getCode().equals(((BirdSpecie) other).getCode());
	}
}
