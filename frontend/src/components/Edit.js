import React, {Component} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {}
        };
    }

    componentDidMount() {
        axios.get('/users/' + this.props.match.params.id)
            .then(res => {
                this.setState({user: res.data});
                console.log(this.state.user);
            });
    }

    onChange = (e) => {
        const state = this.state.user;
        state[e.target.name] = e.target.value;
        this.setState({user: state});
    };

    onSubmit = (e) => {
        e.preventDefault();

        const {firstname, lastname, email, phonenumber} = this.state.user;

        axios.put('/users/' + this.props.match.params.id, {firstname, lastname, email, phonenumber})
            .then((result) => {
                this.props.history.push("/show/" + this.props.match.params.id)
            });
    };

    render() {
        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Edit User
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4><Link to={`/show/${this.state.user.id}`}><span class="glyphicon glyphicon-eye-open"
                                                                           aria-hidden="true"></span> Users List</Link>
                        </h4>
                        <form onSubmit={this.onSubmit}>
                            <div class="form-group">
                                <label for="firstname">Firstname:</label>
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
                                <label for="phonenumber">Phoneumber:</label>
                                <input type="text" class="form-control" name="phonenumber" onChange={this.onChange}/>
                            </div>
                            <button type="submit" class="btn btn-default">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Edit;
