package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;
import cardexpansionpack.actions.AddFromDiscardWithExclusionsAction;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Salvage extends BaseCard {
    public static final String ID = makeID("Salvage");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int CARDS_TO_RETRIEVE = 2;

    public Salvage() {
        super(ID, INFO);
        setMagic(CARDS_TO_RETRIEVE);
        setCostUpgrade(0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Salvage();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardExpansionPack.logger.info("i am here");
        addToBot(new AddFromDiscardWithExclusionsAction(CARDS_TO_RETRIEVE, ID));
        CardExpansionPack.logger.info("i am queer");
    }
}
