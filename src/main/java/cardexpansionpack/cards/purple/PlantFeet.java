package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class PlantFeet extends BaseCard {
    public static final String ID = makeID("PlantFeet");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        2
    );

    private static final int BLOCK = 9;
    private static final int BLOCK_UPGRADE = 4;

    public PlantFeet() {
        super(ID, INFO);
        setBlock(BLOCK, BLOCK_UPGRADE);
        cardsToPreview = new OnSolidGround();
    }

    @Override
    public AbstractCard makeCopy() {
        return new PlantFeet();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new MakeTempCardInDrawPileAction(new OnSolidGround(), 1, true, true));
    }
}

