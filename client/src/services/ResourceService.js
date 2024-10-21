

import axios from 'axios'

const resourceService = {
  getArtist() {
    return axios.get('/artists');
  },
  getTalent() {
    return axios.get('/talents');
  },
  createTalent(talentName) {
    return axios.post('/talents/create', talentName);
  },
  deleteTalent(talentId) {
    return axios.delete(`/talents/${talentId}/delete`);
  }
};

export { resourceService };
