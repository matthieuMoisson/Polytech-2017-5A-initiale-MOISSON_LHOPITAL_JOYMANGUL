
package fr.polytech.quizz.quizz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Malt {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private Amount amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

}
