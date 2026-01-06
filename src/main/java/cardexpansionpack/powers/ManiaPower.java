package cardexpansionpack.powers;

import java.util.ArrayList;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;

public class ManiaPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("ManiaPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ManiaPower(AbstractCreature owner, int amount) {
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
        if (card.type == CardType.ATTACK) {
            ArrayList<AbstractCard> applicableCards = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce && c.type == CardType.ATTACK) {
                    applicableCards.add(c);
                }
            }

            if (applicableCards.size() == 0) {
                return;
            }

            for (int i = 0; i < amount; i++) {
                int nextCardIndex = AbstractDungeon.cardRng.random(applicableCards.size() - 1);
                AbstractCard nextCard = applicableCards.get(nextCardIndex);
                nextCard.setCostForTurn(nextCard.costForTurn - 1);
            }
        }
    }
}
