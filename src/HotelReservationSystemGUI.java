import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Room {
    int roomNumber;
    String category;
    boolean booked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.booked = false;
    }
}

public class HotelReservationSystemGUI extends JFrame {

    private ArrayList<Room> rooms = new ArrayList<>();

    private JTextArea displayArea;
    private JTextField roomField;

    public HotelReservationSystemGUI() {

        setTitle("Hotel Reservation System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);

        roomField = new JTextField(10);

        JButton viewBtn = new JButton("View Rooms");
        JButton bookBtn = new JButton("Book Room");
        JButton cancelBtn = new JButton("Cancel Booking");

        JPanel panel = new JPanel();

        panel.add(new JLabel("Room No:"));
        panel.add(roomField);
        panel.add(bookBtn);
        panel.add(cancelBtn);
        panel.add(viewBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        viewBtn.addActionListener(e -> displayRooms());
        bookBtn.addActionListener(e -> bookRoom());
        cancelBtn.addActionListener(e -> cancelBooking());

        displayRooms();
    }

    private void displayRooms() {

        StringBuilder sb = new StringBuilder();

        sb.append("HOTEL ROOM STATUS\n\n");

        for(Room room : rooms) {

            sb.append("Room: ")
                    .append(room.roomNumber)
                    .append(" | ")
                    .append(room.category)
                    .append(" | Status: ")
                    .append(room.booked ? "Booked" : "Available")
                    .append("\n");
        }

        displayArea.setText(sb.toString());
    }

    private void bookRoom() {

        try {

            int roomNo = Integer.parseInt(roomField.getText());

            for(Room room : rooms) {

                if(room.roomNumber == roomNo) {

                    if(!room.booked) {

                        room.booked = true;

                        JOptionPane.showMessageDialog(this,
                                "Room Booked Successfully!");

                    } else {

                        JOptionPane.showMessageDialog(this,
                                "Room Already Booked!");
                    }

                    displayRooms();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Room Not Found!");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Enter Valid Room Number!");
        }
    }

    private void cancelBooking() {

        try {

            int roomNo = Integer.parseInt(roomField.getText());

            for(Room room : rooms) {

                if(room.roomNumber == roomNo) {

                    if(room.booked) {

                        room.booked = false;

                        JOptionPane.showMessageDialog(this,
                                "Booking Cancelled!");

                    } else {

                        JOptionPane.showMessageDialog(this,
                                "Room is already available!");
                    }

                    displayRooms();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Room Not Found!");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Enter Valid Room Number!");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new HotelReservationSystemGUI().setVisible(true));
    }
}
