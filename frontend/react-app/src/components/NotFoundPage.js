import '../styles/notfound.css';

const NotFound = () => {
    return (
        <div className="notfound-page">
            <div className="notfound-overlay">
                <h1 className="notfound-title">404</h1>
                <p className="notfound-message">Oops! The page you are looking for doesn't exist.</p>
                <a href="/" className="notfound-button">Go Back Home</a>
            </div>
        </div>
    );
};

export default NotFound;
