package com.selva.taxi.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class AppProperties
{

    private static final Properties properties = new Properties();


    public static void loadProperties( String path)
    {
        try (FileInputStream file = new FileInputStream( path ))
        {
            properties.load(file);
        }
        catch (IOException e)
        {
            System.out.println("Properties file not found or could not be loaded!");
        }
    }



    public static String getString(String key, String defaultValue)
    {
        return properties.getProperty(key, defaultValue);
    }

    public static Optional<String> getString(String key)
    {
        String value = properties.getProperty(key);
        return Optional.ofNullable(value);
    }


    public static int getInt(String key, int defaultValue)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Integer.parseInt(value);
            }
            catch (NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static Optional<Integer> getInt(String key)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Optional.of(Integer.parseInt(value));
            }
            catch (NumberFormatException e)
            {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }



    public static long getLong(String key, long defaultValue)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Long.parseLong(value);
            }
            catch (NumberFormatException e)
            {
                return defaultValue;            }
        }
        return defaultValue;
    }

    public static Optional<Long> getLong(String key)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Optional.of(Long.parseLong(value));
            }
            catch (NumberFormatException e)
            {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }



    public static float getFloat(String key, float defaultValue)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Float.parseFloat(value);
            }
            catch (NumberFormatException e)
            {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static Optional<Float> getFloat(String key)
    {
        String value = properties.getProperty(key);
        if (value != null)
        {
            try
            {
                return Optional.of(Float.parseFloat(value));
            }
            catch (NumberFormatException e)
            {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
