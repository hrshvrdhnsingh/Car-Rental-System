import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId, brand, model;
    private boolean isAvailable;
    private double hourlyRate;

    public Car(String carId, String brand, String model, boolean isAvailable, double hourlyRate) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.hourlyRate = hourlyRate;
        this.isAvailable = isAvailable;
    }

    public String getCarId() {
        return carId;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }

    public double calculatePrice(int days) {
        return days * hourlyRate;
    }

    public boolean isCarAvailable() {
        return isAvailable;
    }
    public void rentCar() {
        isAvailable = false;
    }
    public void returnCar() {
        isAvailable = true;
    }
}


class Customer {
    private String name, id;

    Customer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getCustomerId() {
        return id;
    }
}


class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getDays() {
        return days;
    }
}



class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if(car.isCarAvailable()) {
            car.rentCar();
            Rental temp = new Rental(car, customer, days);
            rentals.add(temp);
        }
        else {
            System.out.println("Car is not available.")
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental toRemove = null;

        for(Rental rental: rentals) {
            if(rental.getCar() == car) {
                toRemove = rental;
                break;
            }
        }

        if(toRemove != null){
            rentals.remove(toRemove);
            System.out.println("Car returned succesfully.");
        }
        else {
            System.out.println("Car was not rented.")
        }
    }


}
public class Main {
    public static void main(String[] args)  {
        System.out.println("Hello and welcome!");
    }
}