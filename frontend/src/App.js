import React from 'react';
import Header from './Header';
import Banner from './Banner';
import UpcomingTournaments from './UpcomingTournaments';
import FeaturedTeams from './FeaturedTeams';
import LatestMatches from './LatestMatches';
import Footer from './Footer';

function App() {
    return (
        <div className="App">
            <Header />
            <Banner />
            <UpcomingTournaments />
            <FeaturedTeams />
            <LatestMatches />
            <Footer />
        </div>
    );
}

export default App;
