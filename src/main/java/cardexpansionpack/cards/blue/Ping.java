package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.GoldPlatedCables;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Ping extends BaseCard {
    public static final String ID = makeID("Ping");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    public Ping() {
        super(ID, INFO);
        setCostUpgrade(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ping();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.orbs.isEmpty()) {
            return;
        }

        for (AbstractOrb orb : p.orbs) {
            orb.onStartOfTurn();
            orb.onEndOfTurn();
        }

        AbstractOrb nextOrb = p.orbs.get(0);
        if (p.hasRelic(GoldPlatedCables.ID) && !(nextOrb instanceof EmptyOrbSlot)) {
            nextOrb.onStartOfTurn();
            nextOrb.onEndOfTurn();
        }
    }
}

