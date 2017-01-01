package org.gurpsdomain.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Map;

public class MapOfMapMatcher<T> extends TypeSafeMatcher<Map<String, Object>> {
    public static <T> MapOfMapMatcher hasPath(String path, Matcher<T> matcher) {
        return new MapOfMapMatcher(path, matcher);
    }

    private final String propertyPath;
    private final Matcher<T> matcher;

    public MapOfMapMatcher(String propertyPath, Matcher<T> matcher) {
        this.propertyPath = propertyPath;
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Map<String, Object> start) {
        String[] properties = propertyPath.split("\\.");
        Object current = start;
        for (String property : properties) {
            if (current instanceof Map) {
                Map<String, Object> aMap = (Map<String, Object>) current;
                if (aMap.containsKey(property)) {
                    current = aMap.get(property);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return matcher.matches(current);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("to have path ");
        description.appendValue(propertyPath);
        description.appendText(" ");
        matcher.describeTo(description);

    }
}
