package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.actions.SplitRealityAction;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class SplitReality extends BaseCard {
    public static final String ID = makeID("SplitReality");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    public SplitReality() {
        super(ID, INFO);
        setExhaust(true, false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SplitReality();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SplitRealityAction());
    }
}

