#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#include <fstream>
#include <sstream>

using namespace std;

class Room {
public:
    int roomNumber;
    double price;
    string type;
    bool isAvailable;

    Room(int num = 0, double p = 0.0, string t = "", bool available = true)
        : roomNumber(num), price(p), type(t), isAvailable(available) {}

    static string formatPrice(double price) {
        stringstream ss;
        ss << fixed << setprecision(2);
        ss << "$" << price;
        return ss.str();
    }
};

class Customer {
public:
    string firstName;
    string lastName;
    int numberOfGuests;
    string contactNumber;

    Customer(string fName = "", string lName = "", int guests = 0, string contact = "")
        : firstName(fName), lastName(lName), numberOfGuests(guests), contactNumber(contact) {}
};

class Bill {
public:
    string customerName;
    int roomNumber;
    int numberOfNights;
    double roomRate;
    double amountDue;
    string checkInDate;
    string checkOutDate;

    Bill(string name = "", int roomNum = 0, int nights = 0, double rate = 0.0, string checkIn = "", string checkOut = "")
        : customerName(name), roomNumber(roomNum), numberOfNights(nights), roomRate(rate), amountDue(nights* rate),
        checkInDate(checkIn), checkOutDate(checkOut) {}

    void displayBill() {
        cout << left << setw(20) << "Customer Name"
            << setw(15) << "Room Number"
            << setw(20) << "Number of Nights"
            << setw(20) << "Room Rate"
            << setw(20) << "Total Due"
            << setw(20) << "Check-in Date"
            << setw(20) << "Check-out Date" << endl;
        cout << string(140, '-') << endl;
        cout << setw(20) << customerName
            << setw(15) << roomNumber
            << setw(20) << numberOfNights
            << setw(20) << Room::formatPrice(roomRate)
            << setw(20) << Room::formatPrice(amountDue)
            << setw(20) << checkInDate
            << setw(20) << checkOutDate << endl;
    }
};

class Hotel {
private:
    vector<Room> rooms;
    vector<Customer> customers;
    vector<Bill> bills;

public:
    Hotel() {
        initializeRooms();
        loadState();
    }

    ~Hotel() {
        saveState();
    }

    void initializeRooms() {
        ifstream roomFile("rooms.txt");
        if (!roomFile) {
            cerr << "Failed to open rooms.txt for reading." << endl;
            return;
        }
        string line;
        while (getline(roomFile, line)) {
            stringstream ss(line);
            int roomNumber;
            string type;
            double price;

            ss >> roomNumber;
            ss.ignore();
            getline(ss, type, ' ');
            ss >> price;

            rooms.push_back(Room(roomNumber, price, type, true));
        }
        roomFile.close();
    }

    void saveState() {
        ofstream customerFile("customers.txt", ios::out | ios::trunc);
        if (!customerFile.is_open()) {
            cerr << "Failed to open customers.txt for writing." << endl;
            return;
        }
        for (const auto& customer : customers) {
            customerFile << customer.firstName << ","
                << customer.lastName << ","
                << customer.numberOfGuests << ","
                << customer.contactNumber << "\n";
        }
        customerFile.close();
    }

    void loadState() {
        ifstream customerFile("customers.txt");
        if (!customerFile) {
            cerr << "Failed to open customers.txt for reading." << endl;
            return;
        }
        string line;
        while (getline(customerFile, line)) {
            stringstream ss(line);
            string fName, lName, contactNumber;
            int guests;
            getline(ss, fName, ',');
            getline(ss, lName, ',');
            ss >> guests;
            ss.ignore();
            getline(ss, contactNumber);

            customers.push_back(Customer(fName, lName, guests, contactNumber));
        }
        customerFile.close();
    }

    void addCustomerInfo() {
        string firstName, lastName, contactNumber;
        int numberOfGuests;
        cout << "Enter first name: ";
        cin >> firstName;
        cout << "Enter family name: ";
        cin >> lastName;
        cout << "Enter number of guests: ";
        cin >> numberOfGuests;
        cout << "Enter contact number: ";
        cin >> contactNumber;

        customers.push_back(Customer(firstName, lastName, numberOfGuests, contactNumber));
        saveState();
        cout << "Customer information added successfully and saved.\n";
        cout << "----------------------------------------------------------------------\n";
    }

    void displayCustomerInfo() {
        if (customers.empty()) {
            cout << "No customer information available.\n";
            return;
        }
        cout << left << setw(15) << "First Name"
            << setw(15) << "Family Name"
            << setw(20) << "Number of Guests"
            << setw(20) << "Contact Number" << endl;
        cout << string(70, '-') << endl;
        for (const Customer& customer : customers) {
            cout << setw(15) << customer.firstName
                << setw(15) << customer.lastName
                << setw(20) << customer.numberOfGuests
                << setw(20) << customer.contactNumber << endl;
        }
        cout << "----------------------------------------------------------------------\n";
    }

    void viewAvailableRooms() {
        cout << left << setw(20) << "Room Type"
            << setw(15) << "Room Number"
            << setw(15) << "Price" << endl;
        cout << string(50, '-') << endl;

        for (const Room& room : rooms) {
            if (room.isAvailable) {
                cout << left << setw(20) << room.type
                    << setw(15) << room.roomNumber
                    << setw(15) << Room::formatPrice(room.price) << endl;
            }
        }
        cout << "----------------------------------------------------------------------\n";
    }

    void viewBookedRooms() {
        cout << left << setw(20) << "Room Type"
            << setw(15) << "Room Number"
            << setw(15) << "Price"
            << setw(20) << "Booked By" << endl;
        cout << string(70, '-') << endl;

        for (const Bill& bill : bills) {
            if (!rooms[bill.roomNumber - 101].isAvailable) {
                cout << left << setw(20) << rooms[bill.roomNumber - 101].type
                    << setw(15) << rooms[bill.roomNumber - 101].roomNumber
                    << setw(15) << Room::formatPrice(rooms[bill.roomNumber - 101].price)
                    << setw(20) << bill.customerName << endl;
            }
        }
        cout << "----------------------------------------------------------------------\n";
    }

    void checkInCustomer() {
        string firstName, checkInDate, checkOutDate;
        int roomNumber, numberOfNights;
        cout << "Enter customer's first name (must be existing customer): ";
        cin >> firstName;
        cout << "Enter room number to check-in: ";
        cin >> roomNumber;
        cout << "Enter number of nights: ";
        cin >> numberOfNights;
        cout << "Enter check-in date (DD-MM-YY): ";
        cin.ignore();
        getline(cin, checkInDate);
        cout << "Enter check-out date (DD-MM-YY): ";
        getline(cin, checkOutDate);

        bool customerExists = false, roomFound = false;
        for (const auto& customer : customers) {
            if (customer.firstName == firstName) {
                for (auto& room : rooms) {
                    if (room.roomNumber == roomNumber && room.isAvailable) {
                        room.isAvailable = false;
                        bills.push_back(Bill(firstName, roomNumber, numberOfNights, room.price, checkInDate, checkOutDate));
                        bills.back().displayBill();
                        cout << "Check-in successful and bill generated.\n";
                        roomFound = true;
                        break;
                    }
                }
                customerExists = true;
                break;
            }
        }

        if (!customerExists) {
            cout << "No customer with the given first name exists. Check-in cancelled.\n";
        }
        if (!roomFound && customerExists) {
            cout << "Room not available or does not exist.\n";
        }
        cout << "----------------------------------------------------------------------\n";
    }

    void checkOutCustomer() {
        int roomNumber;
        cout << "Enter room number to check-out: ";
        cin >> roomNumber;

        bool roomCheckedOut = false;
        for (auto& room : rooms) {
            if (room.roomNumber == roomNumber && !room.isAvailable) {
                room.isAvailable = true;
                roomCheckedOut = true;
                for (auto it = bills.begin(); it != bills.end(); ++it) {
                    if (it->roomNumber == roomNumber) {
                        cout << "Enter actual check-out date (DD-MM-YY): ";
                        string checkOutDate;
                        cin.ignore();
                        getline(cin, checkOutDate);
                        it->checkOutDate = checkOutDate;
                        it->displayBill();
                        bills.erase(it);
                        break;
                    }
                }
                cout << "Room " << roomNumber << " is now available again. Checkout successful." << endl;
                break;
            }
        }
        if (!roomCheckedOut) {
            cout << "Room number does not exist or is already available." << endl;
        }
        cout << "----------------------------------------------------------------------\n";
    }
};

int main() {
    Hotel hotel;
    int option;
    do {
        cout << "0. Exit" << endl;
        cout << "1. Add Customer Information" << endl;
        cout << "2. Display Customer Information" << endl;
        cout << "3. View Available Rooms" << endl;
        cout << "4. View Booked Rooms" << endl;
        cout << "5. Check-in Customer" << endl;
        cout << "6. Check-out Customer" << endl;
        cout << "Enter option: ";
        cin >> option;

        switch (option) {
        case 0:
            cout << "Exiting system." << endl;
            return 0;
        case 1:
            hotel.addCustomerInfo();
            break;
        case 2:
            hotel.displayCustomerInfo();
            break;
        case 3:
            hotel.viewAvailableRooms();
            break;
        case 4:
            hotel.viewBookedRooms();
            break;
        case 5:
            hotel.checkInCustomer();
            break;
        case 6:
            hotel.checkOutCustomer();
            break;
        default:
            cout << "Invalid option, please try again." << endl;
        }
    } while (option != 0);

    return 0;
}
