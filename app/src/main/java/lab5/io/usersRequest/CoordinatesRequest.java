package lab5.io.usersRequest;

import lab5.io.console.Console;

import java.util.function.Predicate;

import lab5.collection.ticket.Coordinates;

/**
 * The CoordinatesRequest class is responsible for handling user requests to input
 * coordinates. It extends the Request class and provides methods to create a 
 * Coordinates object based on user input.
 */
public class CoordinatesRequest extends Request<Coordinates> {

    /**
     * Constructs a new CoordinatesRequest with the specified console for user interaction.
     *
     * @param console the console instance used for input and output
     */
    public CoordinatesRequest(Console console) {

        super(console);
    }

    /**
     * Prompts the user to input X and Y coordinates and creates a Coordinates object.
     *
     * @return a Coordinates object containing the user-provided values, or null if the input is invalid
     */
    @Override
    public Coordinates create() {

        Predicate<Double> predicateX = x -> (x != null);
        double X = askNumericValue("X coordinates", null, predicateX, Double.class);

        if (!predicateX.test(X))
            return null;

        Predicate<Float> predicateY = y -> (y != null && y > -11);
        float Y = askNumericValue("Y coordinates", "значение должно быть больше -11", predicateY, Float.class);

        if(!predicateY.test(Y)) 
            return null;
        
        return new Coordinates(X, Y);
    }
}
