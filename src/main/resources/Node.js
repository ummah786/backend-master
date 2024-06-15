const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");

const app = express();
const server = http.createServer(app);

const io = socketIo(server, {
    cors: {
        origin: "*",
        methods: ["GET", "POST"]
    }
});

app.use(cors());
const PORT = process.env.PORT || 8700;

app.get('/', (req, res) => {
    res.send('Hello World');
});

io.on("connection", (socket) => {
    console.log(`New client connected: ${socket.id}`);

    // Send the client's own ID
    socket.emit("me", socket.id);

    socket.on("disconnect", () => {
        console.log(`Client disconnected: ${socket.id}`);
        socket.broadcast.emit("callEnded");
    });

    socket.on("callUser", ({ userToCall, signalData, from, name }) => {
        console.log(`callUser event: from ${from} to ${userToCall}, name: ${name}`);
        io.to(userToCall).emit("callUser", { signal: signalData, from, name });
    });

    socket.on("answerCall", (data) => {
        console.log(`answerCall event: from ${data.to}`);
        io.to(data.to).emit("callAccepted", data.signal);
    });
});

server.listen(PORT, () => console.log(`Server is running on port ${PORT}`));