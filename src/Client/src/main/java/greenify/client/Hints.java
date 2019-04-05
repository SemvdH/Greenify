package greenify.client;

import java.util.ArrayList;
import java.util.Random;

public class Hints {
    public ArrayList<String> hints;

    public Hints() {
        this.hints = new ArrayList<String>();
        initHints();
    }

    /**
     * This method adds all the Strings to the arraylist.
     */
    private void initHints() {
        this.hints.add("Buying local produce will not only decrease your carbon "
                + "footprint, but it will also help your local economy: A win-win!");
        this.hints.add("Did you know that a gas oven only uses 6% of its energy "
                + "to cook? And an electric oven is not much better at 12%!");
        this.hints.add("70% of the deforestation of the Amazon rainforest is to provide land for cattle ranches.");
        this.hints.add("Research shows that reducing meat consumption can increase"
                + " your life span by 3,6 years");
        this.hints.add("Vegetarians have a lower risk of getting: Heart disease, high blood pressure, "
                + "diabetes and cancer than meat eaters.");
        this.hints.add("The carbon footprint of a vegetarian diet is about half "
                + "that of a meat-lover’s diet!");
        this.hints.add("Cycling is good for the environment and for your body, "
                + "so why not grab your bike instead of your car?");
        this.hints.add("If we could capture all of the sun’s energy shining on the Earth for just one "
                + "hour, we could power the entire world for one whole year!");
        this.hints.add("27,000 trees are cut down every day so we can have toilet paper.");
        this.hints.add("Recycle glass bottles! A glass bottle made now will take "
                + "more than 4,000 years to decompose.");
        this.hints.add("Don't forget to turn off the lights and heating in rooms"
                + " you're not using at the moment. Save some energy!");
        this.hints.add("Did you know that about 4,5% of the Dutch population does not eat meat?");
        this.hints.add("Reuse your bags when you go grocery shopping. You will save "
                + "plastic bags and won't have a lot of unused bags at home!");
        this.hints.add("An estimated 250 million trees can be saved each year "
                + "if every published newspaper would be recycled!");
        this.hints.add("About 88,000 jobs were created in 2015 through the wind power sector. "
                + "That is a lot of jobs");
        this.hints.add("You can use LED lights in your home to save energy! "
                + "They make light from about 85% of their energy");
        this.hints.add("If you isolate your home well, it will be warmer, "
                + "and you'll save energy as well! No need to wear sweaters anymore");
        this.hints.add("Do you have leftovers? Donate them to food kitchens."
                + " This way you won't waste"
                + " food, and you'll help a lot of people at the same time!");
        this.hints.add("A lot of coffee places give you a discount if you bring your own cup. "
                + "Get rid of disposable cups!");
        this.hints.add("When shopping, look for products with minimal to no packaging, "
                + "or packaging made from recycled items. ");
        this.hints.add("If you order food, you can ask the restaurant to not include "
                + "utensils and napkins, it saves plastic and paper!");
        this.hints.add("It takes about 66 days to form a new habit, keep going, you'll get there!");
        this.hints.add("Get yourself a nice reusable water bottle! It's cheaper and better for "
               + "the environment to refill than to buy one every time it's empty.");
        this.hints.add("Only 1% of our planet’s water supply can be used a drinkwater."
                + " 97% is ocean water and 2% is frozen solid in the Arctic, for now.");
        this.hints.add("Plastic bad, very bad.");
    }

    /**
     * This method returns a random String.
     * @return the random hint.
     */
    public String randomHint() {
        Random rand = new Random();
        int index = rand.nextInt(this.hints.size());
        return this.hints.get(index);
    }

}
