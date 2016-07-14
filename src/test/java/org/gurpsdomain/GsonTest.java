package org.gurpsdomain;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.StringWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GsonTest {
    @Test
    public void shouldDumpJson() {
        Counter counter = new Counter(10);
        counter.increment();

        Appendable writer = new StringWriter();
        Gson gson = new Gson();
        gson.toJson(counter, writer);

        String result = writer.toString();
        assertThat(result, is("{\"count\":11}"));
    }
}

class Counter {
    private int count;

    public Counter(int startCount) {
        this.count = startCount;
    }

    public void increment() {
        count++;
    }
}