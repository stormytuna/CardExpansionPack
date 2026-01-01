package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.GraspPotentialPower;
import cardexpansionpack.util.CardStats;

public class GraspPotential extends BaseCard {
    public static final String ID = makeID("GraspPotential");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 2;

    public GraspPotential() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new GraspPotential();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, DamageType.NORMAL);
        addToBot(new DamageAction(m, info, AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new GraspPotentialPower(p, 1)));
    }
}

