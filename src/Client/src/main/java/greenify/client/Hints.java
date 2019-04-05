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
                + "footprint, but it will also help your local economy: A win-win!");
        this.hints.add("Did you know that a gas oven only uses 6% of its energy "
                + "to cook? And an electric oven is not much better at 12%!");
        this.hints.add("70% of the deforestation of the Amazon rainforest is to provide land "
                + "for cattle ranches.");
        this.hints.add("Research shows that reducing meat consumption can "
                + "increase your life span by 3,6 years");
        this.hints.add("Vegetarians have a lower risk of getting: Heart disease, high blood "
                + "pressure, diabetes and cancer than meat eaters.");
        this.hints.add("The carbon footprint of a vegetarian diet is about half "
                + "that of a meat-lover’s diet!");
        this.hints.add("Cycling is good for the environment and for your body, "
                + "so why not grab your bike instead of your car?");
        this.hints.add("If we could capture all of the sun’s energy shining on the Earth for just "
                + "one hour, we could power the entire world for one whole year!");
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
        this.hints.add("For every two million tons of paper and glass, collected in "
                + "the Netherlands, close to 90% gets recycled and used to make new products");
        this.hints.add("In the Netherlands: Whenever you purchase any new, electronic or household "
                + "equipment, you will automatically be charged a ‘recycling tax’ on it. This is "
                + "also known as ‘removal tax’, or ‘verwijderingsbijdrage’ in Dutch");
        this.hints.add("Second only to Germany, the Netherlands leads the world in recycling"
                + ", with 65 percent of all waste recycled.");
        this.hints.add("If tbe back of your fridge is dusty, it can increase "
                + "energy consumption by 30 per cent.");
        this.hints.add("Underinflated tyres decrease fuel economy by up to three percent and"
                + " lead to increased pollution and higher greenhouse gas emissions.");
        this.hints.add("Meat production is extremely resource-intensive. "
                +"If you gave it up just once a week, you would save the 840 gallons "
                + "of fresh water it takes to produce a single serving.");
        this.hints.add("Plastic bad, very bad.");
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

