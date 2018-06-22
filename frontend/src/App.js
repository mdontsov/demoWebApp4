import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {Link} from 'react-router-dom';
import axios from 'axios';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
    }

    componentDidMount() {
        axios.get('/users')
            .then(res => {
                this.setState({users: res.data});
                console.log(this.state.users);
            });
    }

    render() {
        return (
            <div class="container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Users List
                        </h3>
                    </div>
                    <div class="panel-body">
                        <h4><Link to="/create"><span class="glyphicon glyphicon-plus-sign"
                                                     aria-hidden="true"></span> Add User</Link></h4>
                        <table class="table table-stripe">
                            <thead>
                            <tr>
                                <th>Firstname</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.users.map(u =>
                                <tr>
                                    <td><Link to={`/show/${u.id}`}>{u.firstname}</Link></td>
                                    <td>{u.email}</td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
