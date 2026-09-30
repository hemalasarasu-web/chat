package com.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String URL =
            "jdbc:mysql://localhost:3306/chatdb";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "Hema123456789";

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String message = request.getParameter("message");

        HttpSession session = request.getSession();
        String sender = (String) session.getAttribute("username");

        if (sender == null) {
            sender = "User";
        }

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            String sql =
                    "INSERT INTO messages(sender, receiver, message) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sender);
            ps.setString(2, "User2");
            ps.setString(3, message);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.getWriter().print("Message sent");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().print(
                    "Error: " + e.getMessage());
        }
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL, USER, PASSWORD);

            String sql =
                    "SELECT sender, receiver, message, message_time "
                    + "FROM messages ORDER BY message_time ASC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println(
                    "<div class='message'>" +
                    "<b>" + rs.getString("sender") + "</b> → " +
                    "<b>" + rs.getString("receiver") + "</b><br>" +
                    rs.getString("message") +
                    "<br><span class='time'>" +
                    rs.getString("message_time") +
                    "</span></div>"
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.println(
                "<p style='color:red;'>Database Error: "
                + e.getMessage() + "</p>"
            );
        }
    }
}