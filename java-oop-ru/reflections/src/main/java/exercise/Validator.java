package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {

        List<String> notValidatedFields = new ArrayList<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        notValidatedFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return notValidatedFields;
    }

    public static Map<String, List<String>> advancedValidator(Address address) {
        Map<String, List<String>> notValidatedFields = new LinkedHashMap<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            MinLength minLength = field.getAnnotation(MinLength.class);
            field.setAccessible(true);

            String key = field.getName();
            List<String> value = new ArrayList<>();

            if (notNull != null) {
                try {
                    if (field.get(address) == null) {
                        value.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            if (minLength != null) {

                try {
                    String fieldValue = (String) field.get(address);
                    if (fieldValue != null) {
                        if (fieldValue.length() < minLength.minLength()) {
                            value.add("length less than " + minLength.minLength());
                        }
                    } else {
                        value.add("length less than " + minLength.minLength());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }

            if (!value.isEmpty()) {
                notValidatedFields.put(key, value);
            }
        }

        return notValidatedFields;
    }
}
// END
