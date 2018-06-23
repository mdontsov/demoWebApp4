import React, {Component} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Create extends Component {

    constructor(props) {
        super(props);
        this.state = {
            firstname: '',
            lastname: '',
            email: '',
            phonenumber: ''
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onSubmit(event) {
        event.preventDefault();

        const {firstname, lastname, email, phonenumber} = this.state;

        axios.post('/users', {firstname, lastname, email, phonenumber})
            .then((result) => {
                this.props.history.push("/")
            });
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    render() {
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
                                <input type="text" class="form-control" name="firstname" onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="lastname">Lastname:</label>
                                <input type="text" class="form-control" name="lastname" onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" class="form-control" name="email" onChange={this.onChange}/>
                            </div>
                            <div class="form-group">
                                <label for="phonenumber">Phonenumber:</label>
                                <input type="text" class="form-control" name="phonenumber" onChange={this.onChange}/>
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
