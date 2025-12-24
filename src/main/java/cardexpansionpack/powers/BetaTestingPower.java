package cardexpansionpack.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;

import cardexpansionpack.CardExpansionPack;

public class BetaTestingPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("BetaTestingPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BetaTestingPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (!(owner instanceof AbstractPlayer)) {
            return;
        }
        AbstractPlayer player = (AbstractPlayer)owner;

        flash();

        int passiveAmount = amount;
        if (player.orbs.get(0) == orb && player.hasRelic(GoldPlatedCables.ID)) {
            passiveAmount++;
        }
            
        for (int i = 0; i < passiveAmount; i++) {
            orb.onEndOfTurn();
            orb.onStartOfTurn();
        }
    }
}
