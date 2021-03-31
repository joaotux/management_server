package br.com.umdesenvolvedor.management_server.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {

	public static Object copyAttributesFromTo(Object a, Object b) {
		Field[] fieldsFromFirstClass = a.getClass().getDeclaredFields();
		Field[] fieldsFromSecondClass = b.getClass().getDeclaredFields();

		for (Field currentFieldFromTheFirstClass : fieldsFromFirstClass) {
			for (Field currentFieldFromTheSecondClass : fieldsFromSecondClass) {
				String nameOfTheFirstField = currentFieldFromTheFirstClass.getName();
				String nameOfTheSecondField = currentFieldFromTheSecondClass.getName();

				if (nameOfTheFirstField.equals(nameOfTheSecondField)) {
					currentFieldFromTheFirstClass.setAccessible(true);
					currentFieldFromTheSecondClass.setAccessible(true);

					try {
                        currentFieldFromTheFirstClass.set(a, currentFieldFromTheSecondClass.get(b));
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
				}
			}
		}

		return a;
	}
}
