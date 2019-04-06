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
     * This method adds all the Strings to the array list.
     */
    private void initHints() {
        this.hints.add("Buying local produce will not only decrease your carbon "
                + "footprint, but also help your local economy: A win-win!");
        this.hints.add("Did you know that a gas oven only uses 6% of its energy "
                + "to cook? And an electric oven is not much better at 12%.");
        this.hints.add("70% of the deforestation of the Amazon is to provide land for "
                + "cattle ranches.");
        this.hints.add("Research shows that reducing meat consumption can increase"
                + " your life span by 3.6 years");
        this.hints.add("Vegetarians have a lower risk of getting heart disease, high blood "
                + "pressure, diabetes and cancer than meat eaters.");
        this.hints.add("Did you know? The carbon footprint of a vegetarian diet is about half "
                + "that of a meat-lover’s diet!");
        this.hints.add("Cycling is good for the environment AND for your body, "
                + "so why not grab your bike instead of your car?");
        this.hints.add("If we could capture all of the sun’s energy shining on the Earth"
                + " for just one hour, we could power the entire world for one year!");
        this.hints.add("27,000 trees are cut down each day so we can have toilet paper.");
        this.hints.add("A glass bottle made in our time will take more than 4,000 years "
                + "to decompose.");
        this.hints.add("Don't forget to turn off the lights and heating in rooms"
                + " you're not using at the moment!");
        this.hints.add("Did you know that about 4.5% of the Dutch population does not eat meat?");
        this.hints.add("Reuse your bags when you go grocery shopping. You will save plastic bags!");
        this.hints.add("An estimated 250 million trees can be saved each year "
                + "if every published newspaper would be recycled.");
        this.hints.add("About 88,000 jobs were created in 2015 through the wind power sector.");
        this.hints.add("You can use LED lights in your home to save energy!");
        this.hints.add("If you isolate your home well, it will be warmer, "
                + "and you'll save energy as well!");
        this.hints.add("Do you have leftovers? Donate them to food kitchens."
                + " This way you won't waste"
                + " food, and you'll help people at the same time!");
        this.hints.add("A lot of coffee places give you a discount if you bring your own cup. "
                + "Get rid of those disposable cups!");
        this.hints.add("When shopping, look for products with minimal to no packaging, "
                + "or at least packaging made from recycled items. ");
        this.hints.add("If you order food, you can ask the restaurant to not include "
                + "utensils and napkins, it saves plastic and paper.");
        this.hints.add("It takes about 66 days to form a new habit, keep going!");
        this.hints.add("Get yourself a nice reusable water bottle! It's cheaper and better for "
               + "the environment to refill than to buy a new one every time it's empty.");
        this.hints.add("Recycle glass bottles!"
                + " A glass bottle made in our time will take more than 4,000 years"
                + " to decompose.");
        this.hints.add("Only 1% of our planet’s water supply can be used."
                + " 97% is ocean water and 2% is frozen solid in the Arctic, for now.");
        this.hints.add("Plastic bad");
    }

    /**
     * This method gets a random String.
     * @return the random hint.
     */
    public String randomHint() {
        Random rand = new Random();
        int index = rand.nextInt(this.hints.size());
        return this.hints.get(index);
    }

}
