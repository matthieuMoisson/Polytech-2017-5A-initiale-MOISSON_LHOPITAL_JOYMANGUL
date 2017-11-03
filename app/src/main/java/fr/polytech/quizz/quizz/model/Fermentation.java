
package fr.polytech.quizz.quizz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fermentation {

    @SerializedName("temp")
    @Expose
    private Temp temp;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

}
