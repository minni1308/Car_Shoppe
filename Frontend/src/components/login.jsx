import React from 'react'
import { connect } from 'react-redux';
import { login } from './Auth';
import { loginUser } from '../actions/users';
import axios from 'axios';
import '../css/LoginStyle.css';

const USERS_REST_API_PREFIX = 'http://localhost:8080/customers/';


class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            clientId: '',
            error: undefined,
            loading: false
        };
        this.onClickLoginButton = this.onClickLoginButton.bind(this);
        this.updateClientId = this.updateClientId.bind(this);
        this.onClickLogin = this.onClickLogin.bind(this);
        this.authenticateData = this.authenticateData.bind(this);
    }

    updateClientId(e) {
        this.setState({ clientId: e.target.value });
        console.log(`user:${this.state.clientId}`);
    }
    updatePassword(e) {
        this.setState({ password: e.target.value });
        console.log(`passw: ${this.state.password}`);
    }
    authenticateData(url) {
        axios.get(url).then((response) => {
            console.log(response);
            this.props.dispatch(loginUser(response.data));
            login(response.data);
            this.props.history.push('/cars');
        })
            .catch(error => {
                console.log(error);
                this.setState({
                    error: "User Not Found"
                })
            })
        this.setState(() => ({ loading: false }));
    }
    onClickLogin() {
        //e.preventdefault();
        if (this.state.clientId == "admin") {
            login("admin");
            this.props.history.push('/admin');
        }
        else if (this.state.clientId[0] == 'd') {
            login({
                dealerid: this.state.clientId //if "dealerid" name changed, change in auth.js (isDealer)
                //change this similar to customer after setting-up API 
            });
            this.props.history.push('/dealerpage');
        }
        else if (this.state.clientId) {
            this.setState({ loading: true });
            this.setState({ error: undefined });
            this.authenticateData(USERS_REST_API_PREFIX + this.state.clientId);
        }
    }

    onClickLoginButton() {
        if (this.state.clientId) {
            console.log(this.state.clientId);
            let clientVar = this.state.clientId;
            if (clientVar < 200) {
                let flag = true;
                for (let i = 0; i < this.props.userData.length; i++) {
                    if (this.props.userData[i].clientId == clientVar) {
                        console.log("Success");
                        alert(`Welcome ${clientVar}`);
                        login(this.state.clientId);
                        this.props.history.push('/homepage');
                        flag = !flag;
                        break;
                    }
                }
                if (flag) {
                    alert("Enter correct");
                    console.log("NOT");
                }
            }
            else if (clientVar >= 200 && clientVar < 300) {
                //Login for Dealer
                this.props.history.push("/dealer");
            }
            else if (clientVar >= 300 && clientVar < 400) {
                //Login for Admin
                this.props.history.push("/admin");
            }


        }
        else {
            console.log("input field is empty");
            this.props.history.push("/login");
        }
    }

    // onClickSetPasswordButton(){

    //     //redirect to set password page
    //     this.props.history.push('./setpassword');
    // }
    // onClickForgotPasswordButton(){
    //     //redirect to forgot password page
    //     this.props.history.push('./forgotpassword');

    // }

    render() {
        return (
            <div>

                <div className="limiter">
                    <div className="cont">
                        <div className="wrap">
                            {/* <form className="login-form"> */}
                            <span className="login-form-title">
                                Car Shoppe
                            </span>
                            <div className="wrap-input">
                                {/*<p>Dear {localStorage.getItem("roleName")} please enter your ID to login </p><br/>
                    <p>{localStorage.getItem("roleName")} ID: </p>*/}
                                <input className="input" type="text" name="cid" onChange={this.updateClientId} />
                                <span className="focus-input" data-placeholder="Customer ID"></span >
                            </div>
                            {!!this.state.error && <p>{this.state.error}</p>}
                            <div className="cont-form-btn">
                                <div className="wrap-form-btn">
                                    <div className="login-form-bgbtn"></div>
                                    <button onClick={() => { this.onClickLogin() }} className="login-form-btn">
                                        Login
                                    </button>
                                </div>
                            </div>

                            {/* </form> */}
                        </div>
                    </div>
                </div>

                {/* Password: <input style={{ marginTop:"5%",marginBottom:"5%"}} type="password" onChange={this.updatePassword}/><br/> */}

                {/* <p>Logging in for the first time?</p> */}
                {/* <button id="setPassword" onClick={this.onClickSetPasswordButton} style={{backgroundColor:"red",color:"white", marginBottom:"5%"}}>Set Password</button><br/> */}
                {/* <button style={{ backgroundColor:"red",color:"white",transform:"translateX(70%)",marginBottom:"5%",marginTop:"5%"}} onClick={this.onClickForgotPasswordButton}>Forgot Password?</button> */}
                {/* <h1>Client ID: {this.state.clientId}</h1>
                <h1>Password: {this.state.password}</h1> */}


            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        userData: state.loginData
    };
}



export default connect(mapStateToProps)(Login);