package cardexpansionpack.cards.blue;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class EventHorizon extends BaseCard {
    public static final String ID = makeID("EventHorizon");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        2
    );

    public EventHorizon() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EventHorizon();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.orbs.isEmpty()) {
            return;
        }

        if (upgraded) {
            for (AbstractOrb orb : p.orbs) {
                if (orb instanceof Dark) {
                    orb.onStartOfTurn();
                    orb.onEndOfTurn();
                }
            }

            AbstractOrb nextOrb = p.orbs.get(0);
            if (p.hasRelic(GoldPlatedCables.ID) && nextOrb instanceof Dark) {
                nextOrb.onStartOfTurn();
                nextOrb.onEndOfTurn();
            }
        }

        for (int i = 0; i < p.orbs.size(); i++) {
            AbstractOrb nextOrb = p.orbs.get(i);
            if (nextOrb instanceof Dark) {
                addToBot(new EvokeSpecificOrbAction(nextOrb));
            }
        }
    }
}

