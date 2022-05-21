var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
var helmet = require("helmet");
require("./database/config");
var authRouter = require("./routers/auth");
var passport = require("passport");
require("./auth/auth");
var userRouter = require("./routers/user");
var wordRouter = require("./routers/word");
var errorHandler = require("./utils/errorHandler");
var hasrole = require("./hasrole")

var app = express();

app.use(logger("dev"));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));
// Protecting all routes
app.use(authRouter);
app.use(passport.authenticate("jwt", { session: false }))
app.use("/users", hasrole("admin"), userRouter);
app.use("/words", wordRouter);
app.use(errorHandler);

module.exports = app;