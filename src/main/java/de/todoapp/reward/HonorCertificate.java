package de.todoapp.reward;

public class HonorCertificate extends BaseReward {

    public HonorCertificate() {
        id = "honorCertificate";
    }

    @Override
    public boolean gainedReward(int points) {
        if (points >= 450) {
            return true;
        }
        return false;
    }
}

