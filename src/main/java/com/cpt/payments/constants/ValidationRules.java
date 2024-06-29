package com.cpt.payments.constants;

import com.cpt.payments.service.impl.validation.ValidationCheck1;
import com.cpt.payments.service.impl.validation.ValidationCheck2;

public enum ValidationRules {
	
	VALIDATION_CHECK1("VALIDATION_CHECK1", ValidationCheck1.class ),
	VALIDATION_CHECK2("VALIDATION_CHECK2", ValidationCheck2.class);

    private final String name;
    private final Class<?> clazz;

    ValidationRules(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public static ValidationRules fromName(String name) {
        for (ValidationRules type : ValidationRules.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        
        return null;
    }
	
}



