import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Create extends Component {

    constructor() {
        super();
        this.state = {
            firstname: '',
            lastname: '',
            email: '',
            phonenumber: ''
        };
    }

    onChange = (e) => {
        const state = this.state;
        state[e.target.firstname] = e.target.value;
        this.setState(state);
    };

    onSubmit = (e) => {
        e.preventDefault();

        const {firstname, lastname, email, phonenumber} = this.state;

        axios.post('/users', {firstname, lastname, email, phonenumber})
            .then((result) => {
                this.props.history.push("/")
            });
    };

    render() {
        const {firstname, lastname, email, phonenumber} = this.state;
        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Add User
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Users List</Link>
                        </h4>
                        <form onSubmit={this.onSubmit}>
                            <div class="form-group">
                                <label for="firstname">Fistname:</label>
                                <input type="text" class="form-control" name="firstname" value={firstname}
                                       onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="lastname">Lastname:</label>
                                <input type="text" class="form-control" name="lastname" value={lastname}
                                       onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" name="email" value={email}
                                       onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="phonenumber">Phonenumber:</label>
                                <input type="text" class="form-control" name="phonenumber" value={phonenumber}
                                       onChange={this.onChange}/>
                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Create;
