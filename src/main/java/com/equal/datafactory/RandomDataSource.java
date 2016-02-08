package com.equal.datafactory;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.fluttercode.datafactory.impl.DataFactory;

import com.equal.annotations.Data;

public class RandomDataSource {

    private DataFactory dataFactory = new DataFactory();
    private Map<String, String> storedData = new HashMap<String, String>();
    private Random random = new Random();

    public void fillEntity(Object entity) {
        if (entity != null) {
            for (Method method : entity.getClass().getMethods()) {
                if (method.getName().startsWith("set")) {
                    if (method.isAnnotationPresent(Data.class)) {
                        Data data = method.getAnnotation(Data.class);
                        try {
                            switch (data.type()) {
                                case NUMERIC:
                                    method.invoke(entity, getNumeric(data.min(), data.max()));
                                    break;
                                case STRING:
                                    if (data.join() != null && !data.join().isEmpty()) {
                                        method.invoke(entity, join(data.join(), getString(data.min(), data.max())));
                                        break;
                                    }
                                    method.invoke(entity, getString(data.min(), data.max()));
                                    break;
                                case ADDRESS:
                                    method.invoke(entity, getAddress());
                                    break;
                                case NAME:
                                    method.invoke(entity, getName());
                                    break;
                                case FIRST_NAME:
                                    method.invoke(entity, getFirstName());
                                    break;
                                case LAST_NAME:
                                    method.invoke(entity, getLastName());
                                    break;
                                case BIRTH_DATE:
                                    method.invoke(entity, getBirthDate());
                                    break;
                                case BUSINESS_NAME:
                                    method.invoke(entity, getBusinessName());
                                    break;
                                case EMAIL:
                                    method.invoke(entity, getEmail());
                                    break;
                                case CITY:
                                    method.invoke(entity, getCity());
                                    break;
                                case STREET:
                                    method.invoke(entity, getStreet());
                                    break;
                                case TEXT:
                                    method.invoke(entity, getText(data.min(), data.max()));
                                    break;
                                case WORD:
                                    method.invoke(entity, getWord(data.min(), data.max()));
                                    break;
                                case CHARS:
                                    method.invoke(entity, getChars(data.min(), data.max()));
                                    break;
                                case BOOLEAN:
                                    method.invoke(entity, getBoolean());
                                    break;
                                case GENDER:
                                    method.invoke(entity, getGender());
                                    break;
                                case ID:
                                    method.invoke(entity, getID());
                                    break;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private String join(String id, String value) {
        if (storedData.containsKey(id)) {
            return storedData.get(id);
        }
        storedData.put(id, value);
        return value;
    }

    public String getNumeric(int min, int max) {
        return String.valueOf(dataFactory.getNumberBetween(min, max));
    }

    public String getString(int min, int max) {
        return dataFactory.getRandomChars(min, max);
    }

    public String getAddress() {
        return dataFactory.getAddress();
    }

    public String getName() {
        return dataFactory.getName();
    }

    public String getFirstName() {
        return dataFactory.getFirstName();
    }

    public String getLastName() {
        return dataFactory.getLastName();
    }

    public String getBirthDate() {
        return dataFactory.getBirthDate().toString();
    }

    public String getBusinessName() {
        return dataFactory.getBusinessName();
    }

    public String getEmail() {
        return dataFactory.getFirstName() + System.currentTimeMillis() + "@mailinator.com";
    }

    public String getCity() {
        return dataFactory.getCity();
    }

    public String getStreet() {
        return dataFactory.getStreetName();
    }

    public String getText(int min, int max) {
        return dataFactory.getRandomText(min, max);
    }

    public String getWord(int min, int max) {
        return dataFactory.getRandomWord(min, max);
    }

    public String getChars(int min, int max) {
        return dataFactory.getRandomChars(min, max) + "T" + 1;
    }

    public String getID() {
        return getChars(2, 3).toUpperCase() + getNumeric(3, 4);
    }

    public String getBoolean() {
        return String.valueOf(random.nextBoolean());
    }

    public String getGender() {
        return random.nextBoolean() ? "Male" : "Female";
    }
}
