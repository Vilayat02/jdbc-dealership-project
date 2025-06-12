package model;

import dao.VehicleDao;
import data.DatabaseConnection;
import data.DealershipFileManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner sc = new Scanner(System.in);
    private DealershipCsv dealershipCsv;
    private List<Vehicle> vehicles;
    private DealershipFileManager fileManager = new DealershipFileManager();
    VehicleDao vehicleDao = new VehicleDao(DatabaseConnection.getDataSource());

    public UserInterface() {
        this.vehicleDao = new VehicleDao(DatabaseConnection.getDataSource());
    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
       // this.dealership = manager.getDealership(); // Load dealership from file
    }

    public void display() throws SQLException {
        init();
        int choice;
        boolean run = true;
        while (run) {
            System.out.println("\nChoose an option:\n1-Find vehicles within a price range\n2-Find vehicles by make/model\n3-Find vehicles by year range\n4-Find vehicles by color\n5-Find vehicles by mileage range\n6-Find vehicles by type (car,truck,SUV,van)\n7-List ALL vehicles\n8-Add a vehicle\n9-Remove a vehicle\n99-Quit");
            int choice1 = sc.nextInt();
            sc.nextLine();
            switch (choice1) {
                case 1:
                processGetByPriceRequest();
                    break;
                case 2:
                processGetByModelrequest();
                    break;
                case 3:
                processGetByYearRequest();
                    break;
                case 4:
                processGetByColorRequest();
                    break;
                case 5:
                processGetByMileageRequest();
                    break;
                case 6:
                processGetByVehicleTypeRequest();
                    break;
                case 7:
                processGetAllVehiclesRequest();
                    break;
                case 8:
                processAddVehicleRequest();
                    break;
                case 9:
                processRemoveVehicleRequest();
                    break;
                case 99:
                    run = false;
                    break;
            }
        }
    }

        public void processGetByPriceRequest () throws SQLException {
            System.out.print("Enter min range:");
            int min = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter max range: ");
            int max = sc.nextInt();
            sc.nextLine();
            try {
                vehicles = vehicleDao.findByPriceRange(min, max);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error fetching by price: " + e.getMessage());
            }
        }
        public void processGetByModelrequest () throws SQLException {
            System.out.print("Enter make:");
            String make = sc.nextLine();
            System.out.print("Enter model: ");
            String model = sc.nextLine();
            try {
                vehicles = vehicleDao.findByMakeAndModel(make, model);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        public void processGetByYearRequest () throws SQLException {
            System.out.print("Enter min range:");
            int minYear = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter max range: ");
            int maxYear = sc.nextInt();
            sc.nextLine();
            try {
                vehicles = vehicleDao.findByYearRange(minYear, maxYear);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        public void processGetByColorRequest () throws SQLException {
            System.out.print("Enter color:");
            String color = sc.nextLine();
            try {
                vehicles = vehicleDao.findByColor(color);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        public void processGetByMileageRequest () throws SQLException {
            System.out.print("Enter min mileage range: ");
            int minMileage = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter max mileage range: ");
            int maxMileage = sc.nextInt();
            sc.nextLine();
            try {
                vehicles = vehicleDao.findByMileageRange(minMileage,maxMileage);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        public void processGetByVehicleTypeRequest () throws SQLException {
            System.out.print("Enter type of car:");
            String type = sc.nextLine();
            try {
                vehicles = vehicleDao.findByType(type);
                displayFormat(vehicles);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        public void processGetAllVehiclesRequest () throws SQLException {
            List<Vehicle> vehicle = vehicleDao.getAllVehicles();
            vehicle.forEach(System.out::println);
        }
        public void processAddVehicleRequest () {
            System.out.print("Please enter vehicle data:\nVin: ");
            int vin = sc.nextInt();
            sc.nextLine();
            System.out.print("Year: ");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.print("Make: ");
            String make1 = sc.nextLine();
            System.out.print("Model: ");
            String model1 = sc.nextLine();
            System.out.print("Vehicle Type (car,truck,SUV,van)");
            String vehicleType = sc.nextLine();
            System.out.print("Color: ");
            String color1 = sc.nextLine();
            System.out.print("Odometer: ");
            int odo = sc.nextInt();
            sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            //Vehicle vehicle = new Vehicle(vin,make1,model1,color1,year,vehicleType,odo,price);
            //dealership.addVehicle(vehicle);
            //fileManager.saveDealership(dealership);
            System.out.println("Vehicle added succesfully!");
        }

        public void processRemoveVehicleRequest () {
            System.out.print("Enter VIN of the vehicle to remove: ");
            int vin = sc.nextInt();
            sc.nextLine();

            try {
                boolean removed = vehicleDao.removeVehicle(vin);
                if (removed) {
                    System.out.println("Vehicle with VIN " + vin + " removed successfully.");
                } else {
                    System.out.println("Vehicle with VIN " + vin + " not found in database.");
                }
            } catch (SQLException e) {
                System.err.println("Error removing vehicle: " + e.getMessage());
            }
        }

    public void displayFormat(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            vehicles.forEach(System.out::println);
        }

        }
    }
