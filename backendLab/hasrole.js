// Middlewere to check if the user has a role
const hasrole = (role) =>
{
    return (req, res, next) => {
        if (req.user.rol === role) {
            next();
        } else {
            res.status(401).send({
                message: "Unauthorized",
            });
        }
    }
}

module.exports = hasrole;