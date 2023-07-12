package com.example.workoutmaker;

import android.util.Log;

public class Utilities {
    public Utilities() {
    }
    
    // Calculates reps based on weight being lifted in relation to 1RM
    public double findReps(double weight, double max){
        double intensity = weight / max;
        double reps;
        // Checks if intensity is greater than 9 rep threshold
        if (intensity >= 0.7915) {
            reps = (-1 * weight/max + 1.0278) / 0.0278; // Applies variation of the Brzychi formula which is most accurate under 9 reps
        }
        // Checks if intensity is less than 9 rep threshold
        else if (intensity <= 0.7637){
            reps = (30*max)/weight -30; // Applies variation of the Epley formula which is  most accurate over 9 reps
        }
        else{reps = 9.0;} // Intensity within the 9 rep range default to 9 reps
        if(reps < 0) {
            reps = 0.0;
        }
        return reps;
    }
    
    // Calculates intensity based on the number of reps the user selects
    public double findIntensity(double reps){
        // Finds intensity based on reps
        double intensity = 0;
        // Checks if reps are greater than 9
        if (reps >= 10) {
            intensity = (30*1)/(30+reps); // Applies variation of the Brzychi formula which is most accurate under 9 reps
        }
        // Checks if reps are less than 9
        else if (intensity <= 8){
            intensity = 1.0278-0.0278 * reps; // Applies variation of the Epley formula which is  most accurate over 9 reps
        }
        else{intensity = 0.7776;} // Defaults to the mean if Brzychi's intensity at 8 reps and Epley's intensity at 10 reps
        return intensity;
    }

    // Calculates weight based on intensity, 1RM, and modified body weight.
    public String calculateWeight (String strReps, String strMax, double modifiedBW){
        double weight = 0;
        double reps = Double.valueOf(strReps);
        double intensity = findIntensity(reps); // Calculates intensity based on reps
        double max = Double.valueOf(strMax);
        String strWeight = "";
        weight = max * intensity - modifiedBW; // Calculates weight based on intensity and 1RM and subtracts modified body weight to represent bar weight only
        double modFive = weight % 5; // Removes the modulus of 5 lbs. from the bar weight
        // Rounds down by 5 lbs. if modulus is less than 2.5 lbs
        if (modFive < 2.5){
            weight = weight - modFive;
        }
        // Rounds up by 5 lbs. if modulus is greater than 2.5 lbs
        else if (modFive >= 2.5){
            weight = weight - modFive + 5;
        }
        return strWeight = String.valueOf(weight);
    }

    // Calculates the total weight being lifted based on intensity and 1RM
    public double calculateRealWeight (String strReps, String strMax){
        double weight = 0;
        double reps = Double.valueOf(strReps);
        double intensity = findIntensity(reps); // Calculatess intensity
        double max = Double.valueOf(strMax);
        String strWeight = "";
        weight = max * intensity;

        return weight;
    }

    // Calculates the 1RM based on weight, intensity, and modified body weight
    public double getMax (double weight, double reps, String modifier){
        double dMod = Double.valueOf(modifier);
        double max = 0;
        double intensity = findIntensity(reps); // Finds intensity
        max = (weight + dMod) / intensity - dMod; // Divides total weight by intensity then subtracts modified body weight to display bar weight to user
        if(max < 0){
            max = 0;
        }
        return max;
    }
    
    // Multiplies body weight by percentage of body weight used in a particular exercise
    public double modifyBodyWeight(String modifier, String strBW){
        double modified_BW = 0;
        double body_weight = Double.valueOf(strBW);
        double dModifier = Double.valueOf(modifier);
        return modified_BW = body_weight * dModifier;
    }
    
    // Rounds reps
    public String calculateReps(double weight, double max){
        double dReps = findReps(weight, max); // Calculates reps
        dReps = Math.round(dReps);
        String reps = String.valueOf(dReps);
        return reps;
    }
}
