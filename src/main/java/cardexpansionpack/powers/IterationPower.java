package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;

import cardexpansionpack.CardExpansionPack;

public class IterationPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("IterationPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public IterationPower(AbstractCreature owner, int amount) {
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
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!(owner instanceof AbstractPlayer)) {
            return;
        }

        AbstractPlayer player = (AbstractPlayer)owner;
        if (player.orbs.isEmpty()) {
            return;
        }

        AbstractOrb nextOrb = player.orbs.get(0);
        if (nextOrb instanceof EmptyOrbSlot) {
            return;
        }

        int triggerCount = amount;
        if (player.hasRelic(GoldPlatedCables.ID)) {
            triggerCount++;
        }

        for (int i = 0; i < triggerCount; i++) {
            nextOrb.onStartOfTurn();
            nextOrb.onEndOfTurn();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
