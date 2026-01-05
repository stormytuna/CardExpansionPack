package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Feint extends BaseCard {
    public static final String ID = makeID("Feint");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int VULNERABLE = 1;
    private static final int VULNERABLE_UPGRADE = 1;
    private static final int NUM_SHIVS = 2;

    public Feint() {
        super(ID, INFO);
        setMagic(VULNERABLE, VULNERABLE_UPGRADE);
        cardsToPreview = new Shiv();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Feint();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        addToBot(new MakeTempCardInHandAction(new Shiv(), NUM_SHIVS));
    }
}
