import {useState} from "react";
import logo from '../images/Logo.png'
import '../css/Login.css'


const Login = ({loading, error, ...props}) => {
    const [values, setValues] = useState({
        username: '',
        password: ''
    });
    const [submitted, setSubmitted] = useState(false);
    const [valid, setValid] = useState(false);

    const handleUsername = (event) => {
        setValues({...values, username: event.target.value})
    }
    const handlePassword = (event) => {
        setValues({...values, password: event.target.value})
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        setSubmitted(true);
        if (values.username && values.password) {
            setValid(true);
        }
        const response = await fetch("http://localhost:8080/auth/users/login", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(values)
        }).catch(err => console.error(err.message))

        const content = await response.json();
        localStorage.setItem("auth", JSON.stringify({
            token: content.token,
            username: content.username
        }))
        if (response.status === 200) {
            window.location.pathname = "/"
        }
    }

    return (
        <>
            <div className="login-container">
                <img src={logo} alt="Logo" className="logo"/>
                <form onSubmit={handleSubmit} className="login-form">
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
                    <button className={'auth-button new-device-button'} type={"submit"}>Login</button>
                </form>
            </div>
        </>
    )
}

export default Login;