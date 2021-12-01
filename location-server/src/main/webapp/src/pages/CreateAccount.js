import {useState} from "react";
import logo from "../images/Logo.png";

const CreateAccount = ({loading, error, ...props}) => {
    const [values, setValues] = useState({
        firstName: '',
        lastName: '',
        email: '',
        username: '',
        password: '',
    });
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);

    const handleUsername = (event) => {
        setValues({...values, username: event.target.value})
    }
    const handleFirstName = (event) => {
        setValues({...values, firstName: event.target.value})
    }
    const handleLastName = (event) => {
        setValues({...values, lastName: event.target.value})
    }
    const handlePassword = (event) => {
        setValues({...values, password: event.target.value})
    }
    const handleEmail = (event) => {
        setValues({...values, email: event.target.value})
    }
    let content;
    const handleSubmit = async (event) => {
        event.preventDefault();
        setSubmitted(true);
        if (values.username && values.password && values.firstName && values.lastName && values.email) {
            setValid(true);
        }
        console.log(JSON.stringify(values))
        const response = await fetch("http://localhost:8080/auth/users/register", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(values)
        }).then(() => window.location.pathname = "/login")

    }

    return (
        <>
            <div className="login-container">
                <img src={logo} alt="Logo" className="logo"/>
                <form onSubmit={handleSubmit} className="login-form">
                    <input
                        className={"form-field"}
                        onChange={handleFirstName}
                        name="firstName"
                        placeholder={"First Name"}
                        value={values.firstName}
                        type="text"
                    />
                    {submitted && !values.firstName ?
                        <span className={"error"}>First name cannot be blank</span> : null}
                    <input
                        className={"form-field"}
                        onChange={handleLastName}
                        name="lastName"
                        placeholder={"Last Name"}
                        value={values.lastName}
                        type="text"
                    />
                    {submitted && !values.lastName ? <span className={"error"}>Last name cannot be blank</span> : null}
                    <input
                        className={"form-field"}
                        onChange={handleEmail}
                        name="lastName"
                        placeholder={"Email"}
                        value={values.email}
                        type="text"
                    />
                    {submitted && !values.email ? <span className={"error"}>Email cannot be blank</span> : null}
                    <input
                        className={"form-field"}
                        onChange={handleUsername}
                        name="Username"
                        placeholder={"Username"}
                        value={values.username}
                        type="text"
                    />
                    {submitted && !values.username ? <span className={"error"}>Username cannot be blank</span> : null}
                    <input
                        className={"form-field"}
                        onChange={handlePassword}
                        name={"password"}
                        placeholder={'Password'}
                        value={values.password}
                        type="password"
                    />
                    {submitted && !values.password ? <span className={"error"}>Password cannot be blank</span> : null}
                    <button className={'auth-button new-device-button'} type={"submit"}>Register</button>
                </form>
            </div>
        </>
    )
}

export default CreateAccount;