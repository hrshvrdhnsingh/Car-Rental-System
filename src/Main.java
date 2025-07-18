import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            System.out.println("Car is not available.");
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
            System.out.println("Car was not rented.");
        }
    }

    public void menu() {
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("----------------Car Rental System---------------");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");

            int choice = in.nextInt();
            in.nextLine();

            if(choice == 1) {
                System.out.println("=====Rent a Car=====");
                System.out.println("Enter your name");
                String name = in.nextLine();

                System.out.println("Available Cars");
                for(Car car: cars) {
                    if(car.isCarAvailable()) {
                        System.out.println(car.getCarId() + ". " + car.getBrand() + "(" + car.getModel() + ")" );
                    }
                }

                System.out.println("Enter the id of the car you want : ");
                String carId = in.nextLine();

                System.out.println("Enter the number of days for which you are about to rent.");
                int days = in.nextInt(); in.nextLine();

                Customer newCustomer = new Customer(name, "CUSTOMER"+(customers.size()+1));
                customers.add(newCustomer);

                Car selectedCar = null;
                for(Car car: cars) {
                    if(car.getCarId().equals(carId) && car.isCarAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if(selectedCar != null) {
                    double totalCost = selectedCar.calculatePrice(days);
                    System.out.println("===== Rental Information =====");
                    System.out.println("Name of Customer : " + newCustomer.getName());
                    System.out.println("Car ID : " + selectedCar.getCarId());
                    System.out.println("Model : " + selectedCar.getModel());
                    System.out.println("Brand : " + selectedCar.getBrand());
                    System.out.println("Rental Days : " + days);
                    System.out.println("Total Cost : " + totalCost);

                    System.out.print("Confirm rental (Y/N) : ");
                    String confirm = in.nextLine();

                    if(confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, days);
                        System.out.println("Car rented successfully.");
                    }
                    else {
                        System.out.println("Thank you for choosing us.");
                    }
                }
                else {
                    System.out.println("Car not available or wrong id selected.");
                }
            }
            else if(choice == 2) {
                System.out.println("===== Return a car =====");
                System.out.print("Enter the carID you want to return : ");
                String carId = in.nextLine();

                Car carToReturn = null;
                for(Car car: cars) {
                    if(car.getCarId().equals(carId)) {
                        carToReturn = car;
                        break;
                    }
                }

                if(carToReturn != null) {
                    Customer returningCustomer = null;
                    for(Rental rental: rentals) {
                        if(rental.getCar() == carToReturn) {
                            returningCustomer = rental.getCustomer();
                            break;
                        }
                    }

                    if(returningCustomer != null) {
                        returnCar(carToReturn);
                        System.out.print("Car was returned succesfully.");
                    }
                    else {
                        System.out.println("Car was not rented or details missing.");
                    }
                }
                else {
                    System.out.println("Invalid carId or car is not rented.");
                }
            }
            else if(choice == 3) {
                break;
            }
            else {
                System.out.println("Invalid choice. Enter again.");
            }
        }

        System.out.println("Thank you for choosing this car rental system.");
    }
}
public class Main {
    public static void main(String[] args)  {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("1", "Mercedes", "AMG F1", true, 1000.00);
        Car car2 = new Car("2", "Redbull", "RB15", true, 8000.50);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);

        rentalSystem.menu();
    }
}